package com.jingran.carpool_backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jingran.carpool_backend.dto.RideDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ride")
public class RideController {

    public static final AtomicLong idGenerator = new AtomicLong(1);

    public static final List<RideDto> allRides = new CopyOnWriteArrayList<>();

    static {
        RideDto ride = new RideDto();
        ride.id = idGenerator.getAndIncrement();
        ride.username = "Lucy";
        ride.brand = "Toyota";
        ride.title = "Ride to Downtown";
        ride.date = "2024-07-01";
        ride.time = "18:00";
        ride.departure = "Campus";
        ride.destination = "Downtown";
        ride.slot = 3;
        ride.phone = "123-456-7890";
        ride.details = "Leaving at 6 PM, can take 3 more people.";

        allRides.add(ride);
    }

    @GetMapping("/avaliable-rides")
    public List<RideDto> getAvailableRides(@RequestHeader(value = "Authorization", required = false) String token) {
        List<RideDto> res = new ArrayList<>();

        String currentUser = getCurrentUserFromToken(token);
        if (currentUser == null) {
            return res;
        }

        for (RideDto ride : allRides) {
            if (!ride.username.equals(currentUser)) {
                res.add(ride);
            }
        }

        return res;
    }

    @PostMapping("/post-newride")
    public ResponseEntity<?> createRide(@Valid @RequestBody RideDto rideDto,
            @RequestHeader(value = "Authorization", required = false) String token) {

        String currentUser = getCurrentUserFromToken(token);
        if (currentUser == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Unauthorized: No token provided"));
        }
        boolean exists = allRides.stream().anyMatch(r -> currentUser.equals(r.username) &&
                Objects.equals(r.date, rideDto.date) &&
                Objects.equals(r.time, rideDto.time));
        if (exists) {
            return ResponseEntity.status(409).body(Map.of("error", "Same ride already exist!"));
        }

        rideDto.username = currentUser;
        rideDto.id = idGenerator.getAndIncrement();
        allRides.add(rideDto);

        return ResponseEntity.ok(true);
    }

    @GetMapping("/user-posted-rides")
    public List<RideDto> getUserPostedRides(@RequestHeader(value = "Authorization") String token) {
        String currentUser = getCurrentUserFromToken(token);
        if (currentUser == null) {
            return new ArrayList<>();
        }
        List<RideDto> res = new ArrayList<>();
        for (RideDto ride : allRides) {
            if (ride.username.equals(currentUser)) {
                res.add(ride);
            }
        }
        return res;
    }

    @PostMapping("/remove-ride")
    public ResponseEntity<?> removeRide(@Valid @RequestBody RideDto rideDto,
            @RequestHeader(value = "Authorization", required = false) String token) {
        String currentUser = getCurrentUserFromToken(token);
        if (currentUser == null) {
            return ResponseEntity.status(400).body(Map.of("error", "Unauthorized: No token provided"));
        }
        if (rideDto.date == null || rideDto.time == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Missing required information: date or time."));
        }
        boolean removed = allRides.removeIf(
                r -> r.date.equals(rideDto.date) && r.time.equals(rideDto.time) && r.username.equals(currentUser));
        if (removed) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(400)
                    .body(Map.of("error", "Ride not found or user not authorized to remove this ride"));
        }
    }

    public static String getCurrentUserFromToken(String token) {
        // 现在还没 JWT，就先做一个简单规则让你能联调：
        // - token 为空 => 未登录
        // - token 存在 => 返回固定用户，或你自己定义的 sessionStorage 用户名
        if (token == null || token.isBlank())
            return null;

        // 如果你的前端 token 其实就是 email（临时做法），可以直接返回 token
        // return token;

        // 否则先固定一个用户，保证接口能跑通
        return "Lily";
    }
}
