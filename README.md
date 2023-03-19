# activity-lifecycle-and-state-lab-4

The app deals with lifecycle and various deifferent lifecycle event methods. The lifecycle events used here is:
* onCreate()
* onStart()
* onResume()
* onPause()
* onStop()
* onRestart()
* onDestroy()

During configuration changes the activity is recreated i.e, onCreate is called once again, but the state disappears, in order for the state to be saved onSaveInstanceState()
method should be overridden which would be called before onDestroy() is called. The onSaveInstanceState() provides a bundle which allows the developer to store various data as 
key value pair which later can be retrieved in onCreate() function.

-----------------------------------------------------------

# Working of the Activity application:


![dessert2](https://user-images.githubusercontent.com/110808053/226163584-19d118b2-d32a-41fe-ac61-ccba0a1722b1.gif)
