class Solution {
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        int[] inDegree = new int[n];
        
        for (int[] prereq : prerequisites) {
            adj.putIfAbsent(prereq[0], new ArrayList<>());
            adj.get(prereq[0]).add(prereq[1]);
            inDegree[prereq[1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
       
        List<Set<Integer>> prereqSets = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            prereqSets.add(new HashSet<>());
            if (inDegree[i] == 0) {
                queue.add(i); 
            }
        }
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            for (int neighbor : adj.getOrDefault(current, new ArrayList<>())) {
                
                prereqSets.get(neighbor).add(current);
                prereqSets.get(neighbor).addAll(prereqSets.get(current));
                
   
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        List<Boolean> result = new ArrayList<>();
        for (int[] query : queries) {
            result.add(prereqSets.get(query[1]).contains(query[0]));
        }
        
        return result;
    }
}
