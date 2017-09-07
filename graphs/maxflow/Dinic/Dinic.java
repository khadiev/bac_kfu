import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * Created by Kamil Khadiev on 15.08.2017.
 * Problem: Find maxflow
 * Algorithm: Dinic Algorithm.
 * Solution: Modification of Ford-Falkerson algorithm.  
 */
//T=O(N^2M), T=O(F*M) but for unit graph: T=O(sqrt(N)*M);  S=O(N+M)
public class Dinic {

    static int[][] c,f;
    static List<List<Integer>> nbs;
    static int[] level,r;
    static int n,s,t;

    boolean bfs(){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(s);
        Arrays.fill(level, -1);
        level[s] = 0;
        while (!q.isEmpty() && level[t]==-1){
            int v = q.poll();
            for (Integer i : nbs.get(v)){
                if (c[v][i]-f[v][i]>0 && level[i]==1){
                    level[i] = level[v]+1;
                    q.add(i);
                }
            }
        }
        return level[t]!=-1;
    }

    int dfs(int v, int flow){// солько можно пустить потока из v в t но не больше чем flow
        if (flow == 0) return 0;
        if (v==t) return 0;
        while (r[v]<n){
            int to = nbs.get(v).get(r[v]);
            if (level[t]==level[v]+1 && c[v][to]-f[v][to]>0){
                int pushed = dfs(to, Math.min(flow, c[v][to] - f[v][to]));
                if (pushed>0){
                    f[v][to]+=pushed;
                    f[to][v]-=pushed;
                    return pushed;
                }
            }
            r[v]++;
        }
        return 0;
    }
    int dinic(int s, int t){
        int flow = 0;
        while (bfs()){
            int newFlow = 1;
            Arrays.fill(r,0);
            while (newFlow>0){
                newFlow = dfs(s,INF);
                flow+=newFlow;
            }
        }
        return  flow;
    }
    static int INF = Integer.MAX_VALUE/2;
}
