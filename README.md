# TDD with JUnit and Spring

## Objectives

* Implement TDD techniques with JUnit to implement classes in a Spring Boot app.

## Instructions

Write the first unit test in SongTest.Java:

```JAVA
public class SongTest {
    @Test
    public void getPrettyDurationTest() {
        Song mySong = new Song("Title","Artist",Duration.ofSeconds(205));
        String prettyResult = mySong.getDurationPretty();
        String expectedResult = String.format("%d:%02d:%02d", 0,3,25);
        assertEquals(expectedResult,prettyResult,"getDurationPretty should convert a song's duration in seconds" +
                " into the format HH:mm:ss");
    }
}
```

Complete the method implementation in the Playlist class by implementing TDD in the PlaylistTest class to satisfy the 
below criteria:

1.  Given I have a playlist, 
    When I add a song to the playlist, 
    Then it will appear in my Playlist.
2.  Given I have a playlist,
    When I remove a song in my playlist,
    Then it no longer appears in my playlist.
3.  Given I have a playlist,
    When I move a song in the playlist order,
    Then the playlist will have a new order.
4.  Given I have a playlist,
    When I look at the playlist duration,
    Then it shows me the total length of my playlist in HH:mm::ss format.
5.  Given I have a playlist,
    When I look at the playlist,
    Then I see the songs in "Title - Artist HH:mm:ss\n" format.
        For example:
        
        2 Heads - Coleman Hell 3:32
        
        Lightning Crashes - Live 5:25
        
        Stars - Hum 5:09

#### JUnit 5 Guides
* [Official JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
* [JUnit 5 Annotations](https://devqa.io/junit-5-annotations/)
