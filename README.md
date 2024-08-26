# DSA-Project
Web Page Cache System - LRU Cache Implementation

# Overview
This project implements a Web Page Cache System using the Least Recently Used (LRU) Cache Algorithm. The system efficiently stores frequently accessed web pages and evicts the least recently accessed pages when the cache reaches a predefined size limit. This is especially useful in browsers, databases, and memory management systems where quick access and optimization of space are crucial.

# Features
LRU Cache Algorithm: Ensures the most recently accessed pages are retained while the least recently accessed pages are evicted when capacity is full.
Efficient Access: Uses a combination of Doubly Linked List and HashMap to provide constant time complexity 
O(1) for insertion, lookup, and deletion.
Console-based Interaction: Simulates web page requests and cache management through a console interface.

# Data Structures
Doubly Linked List (DLL): Tracks the order of page accesses, with the most recently used pages at the head and the least recently used pages at the tail.
HashMap: Provides quick access to the web page nodes in the doubly linked list by mapping URLs to their respective nodes.
Use Case
This system mimics how web browsers store web pages to enhance browsing performance by caching frequently visited pages, reducing the need to reload them from the server.

# How It Works
When a web page is requested, the system checks if it is already in the cache:
If it is, the page is moved to the front of the cache as it is the most recently used.
If it isn't, the page is loaded and added to the cache.
If the cache exceeds its capacity, the least recently used page (at the tail of the list) is evicted.
This approach ensures efficient use of the cache space, prioritizing pages that are accessed more frequently.

Example
Here is a sample interaction with the system:

Input:

Access google.com
Access yahoo.com
Access facebook.com
Access google.com (Moves google.com to the most recently used position)
Access twitter.com (Evicts the least recently used page, e.g., yahoo.com)
Output:

The system will display the updated cache contents and which page was evicted (if any).
Future Enhancements
Add functionality for persistent storage of cached pages.
Implement multi-threading for handling concurrent page requests.
Build a user interface for better interaction and visualization of the cache state.

Console Flow:
Enter the cache capacity (number of pages): 3

Choose an option:
1. Access Web Page
2. Display Cache
3. Exit
1
Enter Web Page URL (e.g., google.com, facebook.com): google.com
Page not found in cache, fetching from server: google.com
Page added to cache: google.com

1
Enter Web Page URL (e.g., google.com, facebook.com): facebook.com
Page not found in cache, fetching from server: facebook.com
Page added to cache: facebook.com

1
Enter Web Page URL (e.g., google.com, facebook.com): twitter.com
Page not found in cache, fetching from server: twitter.com
Page added to cache: twitter.com

2
Cache contents (Most Recent to Least Recent):
[twitter.com: Content of twitter.com] [facebook.com: Content of facebook.com] [google.com: Content of google.com]

1
Enter Web Page URL (e.g., google.com, facebook.com): google.com
Page found in cache: google.com -> Content of google.com

1
Enter Web Page URL (e.g., google.com, facebook.com): linkedin.com
Page not found in cache, fetching from server: linkedin.com
Cache is full, evicting least recently used page: google.com
Page added to cache: linkedin.com

2
Cache contents (Most Recent to Least Recent):
[linkedin.com: Content of linkedin.com] [twitter.com: Content of twitter.com] [facebook.com: Content of facebook.com]

1
Enter Web Page URL (e.g., google.com, facebook.com): twitter.com
Page found in cache: twitter.com -> Content of twitter.com

3
Exiting...
