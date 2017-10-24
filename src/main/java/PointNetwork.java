import javafx.util.Pair;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// This structure organizes a distribution of points along with groups of associated directions:
public class PointNetwork {
    Map<Integer,Tuple<PVector,List<PVector>>> net;
    PointNetwork() {
        net = new HashMap<>();
    }
    float meanSpacing() {
        float space = 0;
        int count = 0;
        for(Map.Entry<Integer, Tuple<PVector,List<PVector>>> pair: net.entrySet()) {
            List<PVector> nbrs = pair.getValue().y;
            PVector basePoint = pair.getValue().x.copy();
            for(PVector nbr: nbrs) {
                PVector nbrcopy = nbr.copy();
                space = (1/(count+1))*(count*space + nbrcopy.dist(basePoint));
                count++;
            }
        }
        return space;
    }
}

