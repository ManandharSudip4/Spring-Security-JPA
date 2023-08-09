# Spring Security - Authentication Configuration with External Database using JPA

### Brief
    Previously i configured InMemory Authentication and JDBC
    Authentication using H2 Database which you can find in this
    follwing repos.
    
   1. [In-Memory Authentication](https://github.com/ManandharSudip4/Spring-Security-Basics)
   2. [JDBC Authentication](https://github.com/ManandharSudip4/Simple-JDBC-Authentication)
   
    So, here i tried to configure this Authentication system
    with external database using JPA.

    I faced a lot of problem understanding the flow how
    authentication will happen, where do all the matching
    happens and all.

    JPA does not contrivute directly for Authentication like
    JDBC does. Instead JPA is only used to fetch the User data
    from the database, create necessary relations in database.

    So , in this 1st phase i have configured authentication
    without using JPA for fetching the user data from the
    database, i've just hardcoded the user data in
    ``MyUserDetails`` Service and returned the user object to
    ``MyUSerDetailsSevice`` Service which inturns returns into
    `UserDetailsService`.

    `Authentication Provider` uses this object returned from
    UserDetailsService to match the credentials and allow
    access accordingly.


## JPA Configuration

    It was fun configuring JPA for Spring Security, I'd already implemented JPa
    with postgres as a database for CRUD operations. 

    Here, i configured authentication and authorization, the credentials is matched with 
    the user data stored in the database rather than comparing with hardcoded user data or in-
    memory authentication.

    I used postgres as a database here.

### General DataFlow ? Work Flow

    Whenever any user login from the login page, the credentials 'username' and 'password' are 
    passed to the Authentication Manager. This Auth Manager delegates the task to specific
    Authentication Provider, which send request to UserDetailsService for the credentials stored 
    in the database.

    This UserDetailsService is reponsible to retrive the required user details form the database and
    send it to respective manager for authentication purpose, all of this happens abstactly.

    UserDetailsService uses JPA Repository API to extract the required user details from the database
    and formats the obtained data  according to UserDetails class before returning back to the Auth
    Manager.

    Overall this is how data flows.

    General workflow of authentication in a Spring application using JPA (Java Persistence API) and an external database. 
    
## Step-by-step breakdown of the process:

1. **Login Page Submission:**
   - User enters their credentials (username and password) on the login page.
   - The credentials are submitted to the server when the user clicks the login button.

2. **Authentication Manager:**
   - The submitted credentials are received by the Authentication Manager.
   - The Authentication Manager delegates the authentication task to a specific Authentication Provider.

3. **Authentication Provider:**
   - The Authentication Provider is responsible for verifying the user's credentials.
   - It requests the user details for the provided username from the UserDetailsService.

4. **UserDetailsService:**
   - The UserDetailsService retrieves the required user details from the external database.
   - It uses the JPA Repository API to query the database for user details based on the provided username.
   - The retrieved user details are formatted into an implementation of the UserDetails class.

5. **UserDetails:**
   - The UserDetails object contains information about the user, such as username, password, roles, and other relevant details.
   - This object is used for authentication and authorization purposes.

6. **Authentication Result:**
   - The Authentication Provider receives the UserDetails object from the UserDetailsService.
   - It compares the provided password with the password stored in the UserDetails object.
   - If the passwords match, authentication is successful; otherwise, it's unsuccessful.

7. **Authentication Manager Response:**
   - The Authentication Provider sends the authentication result (success or failure) back to the Authentication Manager.

8. **Login Success/Failure:**
   - If the authentication is successful, the user is granted access to the application.
   - If the authentication fails, the user is denied access and may be redirected to an error page or the login page again.


## JWT Configuration
   So, i configured jwt for /authenticate end point 
   - whenever we send post request on /authenticate with username and password in body, we get response with JWT token.
