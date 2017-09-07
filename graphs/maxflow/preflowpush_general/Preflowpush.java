import java.util.Arrays;
import java.util.List;

/**
 * Created by Kamil Khadiev on 23.08.2017.
 * Problem: Find maxflow
 * Algorithm: Preflow push Algorithm.
 * Solution: pushing preflows  
 */
//T=O(N^2M)  S=O(N+M)
public class Preflowpush {
static int[][] c,f;
static List<List<Integer>> nbs;
static int[] e,h;
static int n,s,t;

static boolean push(int u,int v){
    if (!(c[u][v]-f[u][v]>0 && e[u]>0 && h[u]==h[v]+1) || u==t ||u==s) return false;
    int d = Math.min(e[u], c[u][v] - f[u][v]);
    f[u][v]+=d;
    f[v][u]-=d;
    e[u]-=d;
    e[v]+=d;
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

static void init(){
    Arrays.fill(e,0);
    Arrays.fill(h,0);
    h[s] = n;
    for (Integer v : nbs.get(s)){
        f[s][v] = c[s][v];
        f[v][s] = -c[s][v];
        e[v] = c[s][v];
    }
}

static int preflowpush(){
    init();
    boolean stop = false;
    while (!stop){
        stop = true;
        for (int i = 0; i < n; i++){
            for(Integer j : nbs.get(i))
             if (push(i,j)) stop = false;
        }
        for (int i = 0; i < n; i++)
            if (lift(i)) stop = false;
    }
    int flow =  e[t];
    return flow;
}
static int INF = Integer.MAX_VALUE/2;
}
