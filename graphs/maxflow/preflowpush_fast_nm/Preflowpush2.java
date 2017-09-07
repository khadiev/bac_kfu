import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * Created by Kamil Khadiev on 23.08.2017.
 * Problem: Find maxflow
 * Algorithm: Preflow push Algorithm with queue and dischargeing.
 * Solution: pushing preflows but discharge vertexes one by one.  
 */
//T=O(NM)  S=O(N+M)
public class Preflowpush2 {

static int[][] c,f;
static List<List<Integer>> nbs;
static int[] e,h,r;
static int n,s,t;
static Queue<Integer> q;

static boolean push(int u,int v) {
    if (!(c[u][v] - f[u][v] > 0 && e[u] > 0 && h[u] == h[v] + 1) || u == t || u == s) return false;
    int d = Math.min(e[u], c[u][v] - f[u][v]);
    f[u][v] += d;
    f[v][u] -= d;
    e[u] -= d;
    if (e[v]==0) q.add(v);
    e[v] += d;
    return true;
}
static boolean lift (int u){
    if (e[u]==0 || u==t || u==s) return false;
    int d = INF;
    for (Integer v: nbs.get(u)){
        if (c[u][v]-f[u][v]>0)
            d = Math.min(d,h[v]);
    }
    if (d==INF) return false;
    h[u] = d+1;
    return true;
}

static int preflowpush(){
    init();
    boolean stop = false;
    while (!q.isEmpty()){
       discharge(q.poll());
    }
    int flow =  e[t];
    return flow;
}

static void discharge(int u){
    while (e[u]>0){
        while (r[u]<nbs.get(u).size()){
            push(u, nbs.get(u).get(r[u]));
            if (e[u]==0) break;
            r[u]++;
        }
        if (lift(u)) r[u] = 0;
    }
}
static void init(){
    Arrays.fill(e,0);
    Arrays.fill(h,0);
    q = new ArrayDeque<>();
    h[s] = n;

    for (Integer v : nbs.get(s)){
        f[s][v] = c[s][v];
        f[v][s] = -c[s][v];
        e[v] = c[s][v];
        if (e[v]>0) q.add(v);
    }
}
static int INF = Integer.MAX_VALUE/2;
}
