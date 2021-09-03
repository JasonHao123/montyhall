#montyhall

##### Problem description:
Assume that you are on a TV-show where you can win money by picking the right box. The host
shows you three boxes and explains that one of them contains money. Then the host asks you
to pick a box without opening it. When you have selected a box the host opens one of the other
two boxes which will be empty. Now you get the question if you want to change your choice or
stick to your original choice of box.
Your task:
Write a program that randomly simulates this game over and over with the purpose to answer
this question: ‘Do I have a better chance to win if I change my box?’

##### Solution:

1. To simulate the game, I create basic models as Game,Host,Guest,GameData,GameStatistics
2. For Monty Hall game, there are concrete classes as MontyHallGame, DefaultMontyHallHost, DefaultMontyHallGuest, MontyHallGameData
3. to simulate different decision policy, I create Strategy, and derived with AlwaysChangeMindStrategy and KeepMyMindStrategy to simulate two types of guest.
4. In this game, MontyHallGame mainly responsible 
   * to reset the status of game
   * an while loop to rotate between host and guest for action, and check whether the game is finished
   * do statics over games, and save the data in GameStatistics, which is mainly about winning ratio
5. Since it's implemented using spring boot, GameConfig try to build two separate games, let host and two types of guest to join respectively. And in the MontyHallApplication it schedule a task in each 500ms to run a round of game for guests.
6. StatisticsController collects game statistics and serve with a html diagram to visualize the realtime performance.

##### Test:
1. Basically the test cases are implemented using junit. 
2. Some of that uses Mockito to mock some of the dependencies.
3. To test the StatisticsController, it uses SpringBootTest with MockMvc
4. dbunit is good to test functions with database connection, but here it's not applicable to demo that.

##### Run the application:
Before running the application, make sure Java >= 1.8, Maven 3.x.x installed.

To run the application, please trigger the command below in project folder
<pre><code>mvn clean spring-boot:run
</code></pre>
then you can see the log in the terminal console

to check the real time performance, open <a href="http://localhost:8080/">http://localhost:8080/</a> in your browser

by default it will run with 3 doors, and host will open 1 empty box, but if you want to check a different option, you can specify extra argument when you run the mvn command as below:
<pre><code>mvn clean spring-boot:run -Dgame.montyhall.number-of-doors=5 -Dgame.montyhall.host.number-of-box-to-open=2
</code></pre>