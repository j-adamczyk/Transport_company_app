# Transport company app
This application is meant to simplify work of a forwarding transport company.  
It allows user to manage drivers, vehicles, invoices (transactions) and transports (past, current and future ones). They can be added, deleted, 
modified etc. Transports can be monitored in real-time with the support of real transport time using Google API. Undo/redo is supported through 
Command pattern.  
Backend uses MongoDB database hosted on Atlas cluster and Google Distance Matrix API, frontend uses JavaFX, complete with an awesome dark theme.  
All connections strings present in historical files are inactive. Please note that secure things like this should NOT be handled this way 
and separate authorization module with information bound to individual accounts should be implemented (we didn't have time for this, since 
we've had a month to create this from scratch).  
This project was created in collaboration with Magdalena Kozub and Natalia Organek.
