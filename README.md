# Spring Security - Authentication Configuration with External Database using JPA

### Brief
    Previously i configured InMemory Authentication and JDBC
    Authentication using H2 Database which you can find in this
    follwing repos.

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
