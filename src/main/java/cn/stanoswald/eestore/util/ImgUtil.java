package cn.stanoswald.eestore.util;

import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class ImgUtil {

    private static final String BASE_IMG_URL = "http://url/";
    private static final String PRODUCT_IMG_DIR = "img/product/";
    private static final String USER_IMG_DIR = "img/user/";

    public static URL saveUserImage(String filename, MultipartFile multipartFile) throws IOException {
        try {
            filename = filename + getExtension(multipartFile);
            save(USER_IMG_DIR, filename, multipartFile);
            return new URL(BASE_IMG_URL + USER_IMG_DIR + filename);
        } catch (Exception e) {
            throw new IOException("file type error");
        }
    }

    public static URL saveProductImage(String filename, MultipartFile multipartFile) throws IOException {
        try {
            filename = filename + getExtension(multipartFile);
            save(PRODUCT_IMG_DIR, filename, multipartFile);
            return new URL(BASE_IMG_URL + PRODUCT_IMG_DIR + filename);
        } catch (Exception e) {
            throw new IOException("file type error");
        }
    }

    protected static String getExtension(MultipartFile file) throws IOException, MimeTypeException {
        String mediaType = new Tika().detect(file.getInputStream());

        MimeType mimeType = TikaConfig.getDefaultConfig().getMimeRepository().forName(mediaType);
        return mimeType.getExtension();
    }

    protected static void save(String destDir, String filename, MultipartFile multipartFile) throws IOException {
        Path destPath = Paths.get(destDir);

        if (!Files.exists(destPath))
            Files.createDirectories(destPath);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = destPath.resolve(filename);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
