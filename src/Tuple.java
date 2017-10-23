import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tuple<X,Y> {
    public final X x;
    public final Y y;
    public Tuple(X x,Y y) {
        this.x = x;
        this.y = y;
    }
    public static <A, B> List<Tuple<A, B>> zipLists(List<A> as, List<B> bs) {
        return IntStream.range(0, Math.min(as.size(), bs.size()))
                .mapToObj(i -> new Tuple<>(as.get(i), bs.get(i)))
                .collect(Collectors.toList());
    }
}
