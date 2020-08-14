POC developed for Agile Engine.

You can run the app as a java application.

Endpoints created:

Post: localhost:8080/api/transactions

Get: localhost:8080/api/transactions

Get: localhost:8080/api/transactions?transactionId={id}

GET: localhost:8080/api/default


The app stores the data into a H2 database:

  URL: http://localhost:8080/h2-console
  
  db url: jdbc:h2:mem:testdb
  
  user: sa
  
  password: password
  
  

TODO / Pending:

Improve UUID Validation.

Add Validations.

Add JUnit testing.
