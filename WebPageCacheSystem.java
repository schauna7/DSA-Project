import java.util.HashMap;
import java.util.Scanner;

// Node class for Doubly Linked List (representing a web page)
class WebPage {
    String url;
    String content;  // Simulating web page content
    WebPage prev;
    WebPage next;

    public WebPage(String url, String content) {
        this.url = url;
        this.content = content;
    }
}

// Web Page Cache System using LRU Cache mechanism
class WebPageCache {
    private HashMap<String, WebPage> cacheMap;  // HashMap to store URL and its corresponding page
    private int cacheCapacity;  // Maximum number of pages the cache can store
    private WebPage head, tail;  // Pointers for the head and tail of the doubly linked list

    public WebPageCache(int capacity) {
        this.cacheCapacity = capacity;
        cacheMap = new HashMap<>();
        head = null;
        tail = null;
    }

    // Method to access a web page by URL
    public void accessPage(String url) {
        if (!cacheMap.containsKey(url)) {
            System.out.println("Page not found in cache, fetching from server: " + url);
            // Simulate fetching content from server
            String content = "Content of " + url;
            addPage(url, content);  // Add the page to the cache
        } else {
            WebPage page = cacheMap.get(url);
            moveToHead(page);  // Move the page to the head of the list (most recently used)
            System.out.println("Page found in cache: " + url + " -> " + page.content);
        }
    }

    // Add a new page to the cache
    private void addPage(String url, String content) {
        if (cacheMap.containsKey(url)) {
            // If the page already exists, update the content and move it to the head
            WebPage page = cacheMap.get(url);
            page.content = content;
            moveToHead(page);
            System.out.println("Page updated in cache: " + url);
        } else {
            WebPage newPage = new WebPage(url, content);

            if (cacheMap.size() == cacheCapacity) {
                // If the cache is full, remove the least recently used page (tail)
                System.out.println("Cache is full, evicting least recently used page: " + tail.url);
                cacheMap.remove(tail.url);
                removeTail();
            }

            // Add the new page to the head of the list
            addToHead(newPage);
            cacheMap.put(url, newPage);
            System.out.println("Page added to cache: " + url);
        }
    }

    // Move the accessed page to the head of the list (most recently used)
    private void moveToHead(WebPage page) {
        if (page == head) {
            return;  // Page is already the most recently used
        }

        // Remove page from current position
        if (page.prev != null) {
            page.prev.next = page.next;
        }

        if (page.next != null) {
            page.next.prev = page.prev;
        }

        if (page == tail) {
            tail = page.prev;
        }

        // Add page to the head of the list
        page.prev = null;
        page.next = head;

        if (head != null) {
            head.prev = page;
        }

        head = page;

        if (tail == null) {
            tail = head;
        }
    }

    // Add a new page to the head of the list
    private void addToHead(WebPage page) {
        page.prev = null;
        page.next = head;

        if (head != null) {
            head.prev = page;
        }

        head = page;

        if (tail == null) {
            tail = head;
        }
    }

    // Remove the least recently used page (tail)
    private void removeTail() {
        if (tail == null) {
            return;
        }

        if (tail.prev != null) {
            tail.prev.next = null;
        } else {
            head = null;
        }

        tail = tail.prev;
    }

    // Display the contents of the cache
    public void displayCache() {
        if (head == null) {
            System.out.println("Cache is empty.");
            return;
        }

        WebPage current = head;
        System.out.println("Cache contents (Most Recent to Least Recent):");

        while (current != null) {
            System.out.print("[" + current.url + ": " + current.content + "] ");
            current = current.next;
        }
        System.out.println();
    }
}

public class WebBrowserCache {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the cache capacity (number of pages): ");
        int capacity = scanner.nextInt();

        WebPageCache webPageCache = new WebPageCache(capacity);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Access Web Page");
            System.out.println("2. Display Cache");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Web Page URL (e.g., google.com, facebook.com): ");
                    String url = scanner.next();
                    webPageCache.accessPage(url);
                    break;

                case 2:
                    webPageCache.displayCache();
                    break;

                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}
