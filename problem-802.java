class Solution {
    public boolean dfs(int[][] graph, int node, int[] visited) {
        
        if (visited[node] == 1) return true;  
        if (visited[node] == 2) return false;
        visited[node] = 1;
        for (int neighbor : graph[node]) {
            if (dfs(graph, neighbor, visited)) {
                return true;
            }
        }

        visited[node] = 2;
        return false;
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        if (graph == null || graph.length == 0) return res;

        int nodeCount = graph.length;
        int[] visited = new int[nodeCount]; 

        for (int i = 0; i < nodeCount; i++) {
            
            if (!dfs(graph, i, visited)) {
                res.add(i);
            }
        }

        Collections.sort(res); 
        return res;
    }
}
