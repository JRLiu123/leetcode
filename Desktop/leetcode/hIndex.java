/**
 * ClassName: hIndex
 * Package: leetcode
 * Description:
 *
 * @Author Jingran Liu
 * @Create 8/12/2024 2:21 PM
 * @Version 1.0
 */
class hIndex {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] count = new int[n+1];

        for(int i = 0; i < n; i++) {
            count[Math.min(n, citations[i])]++;
        }

        for(int h = n; h >= 0; h--) {
            if(h != n){
                count[h] += count[h+1];
            }

            if(count[h] >= h) {
                return h;
            }
        }

        return 0;



    }
}
