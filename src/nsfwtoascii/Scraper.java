package nsfwtoascii;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class Scraper {
    static String[] getCommonCategoryList(){
        String[] categories;
        try {
            Document categoriesDoc = Jsoup.connect("http://www.pornhub.com/albums/female-gay-male-straight").get();
            Elements tagListUl = categoriesDoc.getElementsByClass("tagList");
            Elements tagList = tagListUl.get(0).getElementsByTag("li");
            categories = new String[tagList.size() - 1];
            for (int i = 1; i <= categories.length; ++i) {
                String category = tagList.get(i).child(0).html();
                categories[i - 1] = category;
            }
        }
        catch (IOException ioe) {
            categories = new String[]{"Error loading category list"};
        }

        return categories;
    }

    static String getImageUrlFromCategory(String category) throws IOException {


        try {
            String domain = "http://www.pornhub.com";

            // load the results page for the tag
            Document categoryResultsDoc = Jsoup.connect(domain + "/albums/female-gay-male-misc-straight-transgender-uncategorized?search=" + category).get();
            Elements categoryResults = categoryResultsDoc.getElementsByClass("photoAlbumListContainer");
            int randomGalleryNumber = ThreadLocalRandom.current().nextInt(categoryResults.size());
            Elements randomGallery = categoryResults.get(randomGalleryNumber).getElementsByTag("a");


            String galleryUrl = randomGallery.get(0).attr("href");
            String gallerySizeText = randomGallery.get(0).getElementsByClass("album-photo-counter").html();
            int gallerySize = Integer.parseInt(gallerySizeText.split("\\s+")[0]);
            System.out.println(gallerySize);
            galleryUrl = domain + galleryUrl;
            System.out.println("The randomly chosen gallery page is: " + galleryUrl);

            Document galleryDoc = Jsoup.connect(galleryUrl).get();
            Elements galleryResults = galleryDoc.getElementsByClass("photoAlbumListContainer");
            int randomImageNumber = ThreadLocalRandom.current().nextInt(galleryResults.size());
            Elements randomImage = galleryResults.get(randomImageNumber).getElementsByTag("a");

            String imgPageUrl = randomImage.get(0).attr("href");
            imgPageUrl = domain + imgPageUrl;
            System.out.println("The randomly chosen image is: " + imgPageUrl);

            Document imgDoc = Jsoup.connect(imgPageUrl).get();
            Element imgContainer = imgDoc.getElementById("photoImageSection");
            String imgUrl = imgContainer.getElementsByTag("a").get(2).getElementsByTag("img").attr("src");
            System.out.println("The large image is: " + imgUrl);

            return imgUrl;
        }
        catch (IOException ioe) {
            throw new IOException("Error parsing results.\n" + ioe.getMessage());
        }
    }
}
