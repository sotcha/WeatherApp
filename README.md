# Weather application

The project demonstrates latest android technologies.


## Architecture 
The architecture of the app is based on the concept of Clean Architecture as introducted from Robert C. Martin.(**Uncle Bob**)


#### Why Clean Architecture?

- Separation of Concerns — Separation of code in different modules or sections with specific responsibilities making it easier for maintenance and further modification.
- Loose coupling — Add flexibility to the code without breaking changes.
- Can be tested easily.



##### Layers

The layers used 

###### App(presentation) layer 
Has UI functionality using activities,fragments, views etc.
Communicates with both data and domain layers
###### Domain layer
Contains business logic and does not communicate with no other layer.

###### Data layer
Provides data to the app using network, databases etc
Communicates with domain layer 

#### Other components 

Other components are 
- Use Cases (or interactors). Each individual functionality or business logic unit can be called a use case, like fetching data from the network. The are created to domain layer and presentation layer use the to communicate with data layer.
- Repositories. Interfaces that are created into domain layer and implemented from data layer.


## Technologies used 

- MVVM The presentation layer is based on it.
- View binding
- Room for database persistance 
- Hilt for depentancy injection
- Coroutines 


### Other libraries 
- okhttp for networking 
- glide for images 





