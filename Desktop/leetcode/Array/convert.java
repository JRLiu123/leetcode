package Array;

/**
 * ClassName: convert
 * Package: Array
 * Description:
 *
 * @Author Jingran Liu
 * @Create 8/27/2024 4:30 PM
 * @Version 1.0
 */
public class convert {
    public String convert(String s, int numRows) {
        int n = s.length();
        StringBuilder[] sb = new StringBuilder[numRows];
        for(int i = 0; i < sb.length; i++) {
            sb[i] = new StringBuilder();
        }

        int i = 0;
        while(i < n) {
            for(int idx = 0; idx < sb.length && i < n; idx++) {
                sb[idx].append(s.charAt(i++));
            }

            for(int idx = sb.length-2; idx >= 1 && i < n; idx--) {
                sb[idx].append(s.charAt(i++));
            }
        }

        for(int idx = 1; idx < sb.length; idx++) {
            sb[0].append(sb[idx]);
        }

        return sb[0].toString();
    }
}
