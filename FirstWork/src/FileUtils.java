import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {

	public static List<String> readFileAsList(Path path) throws IOException {
		
		try (Stream<String> lines = Files.lines(path)) {
			return lines.collect(Collectors.toList());
		}
	}
}
