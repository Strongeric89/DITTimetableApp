The following project is the Java CA for Eric Strong. DT211C/2 for OOP Programming Module.

I decided to do an android app that would be of good to use not only to students but also staff. This app is developed in such a way that any student from any university can use this.

The app is a play on a timetable app. It is extremely annoying when you are going from class to class and having to zoom in on your screen shot of your timetable to discover what room you are in and what class is next. Even half way into your semester it is easy to forget which room to go to. Not anymore. The idea of my app is to eleviate this issue and with the click of one button the app will generate what class you are currently in, the module, the remaining minutes and then also the next class room, next module etc...
I did not want to hard code in the timetable and due to permissions students cannot gain access to the Timetable server. So I though id come up with a clever way for students to be able to customize their timetables every year to fit this app. 
I have built in a 'back-door' to the app which allows the student to edit a text file which dynamically reads in the timetable information. Even lecturers can use this app. Give it a go! you find its use full. 

To add to this idea I also wanted a way to store notes for each lecture that I was in so i built in a notepad application with it too. This data also persists to the SQLLite database so when you close down the app your information will still be there. You can specify the priority of your notes so they are sorted in a clever way. 

The app also includes various browser activities out to authenticate onto the DIT timetable system if needed. The app provides the user at run time with instructions to read a link to an instructional video. 


