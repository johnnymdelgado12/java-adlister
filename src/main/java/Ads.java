import java.util.List;

public interface Ads {
    List<Ad> all();

    Ads findById(long id);
}
