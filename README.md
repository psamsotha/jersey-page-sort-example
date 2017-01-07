Jersey Pagination and Sorting Example (with Spring Data JPA)
==============

Source code for blog post [Jersey Pagination with Spring Data JPA][blog-post]

[blog-post]: https://psamsotha.github.io/jersey/2017/01/07/jersey-pagination-with-spring-data.html

### Run

    mvn jetty:run

### Endpoints

| Method | URL | Description |
| --- | --- | --- |
| GET | http://localhost:8080/api/customers | Get all customers |

### Page Parameters

| Parameter | Value | Default |
| --- | --- | --- |
| `page` | The page number (0-based) | 0 |
| `size` | The page size | 3 |

### Sorting

The parameter name is `sort`. There can be multiple instances of this parameter,
each with a value corresponding to a Customer property, e.g. `firstName` or `lastName`.
After each property name, an ordering value may be present, following a comma. The values
of `ASC` (ascending - the default) or `DESC` (descending) as acceptable.

### Example URL

    http://localhost:8080/api/customers?page=1&size=2&sort=firstName,DESC

The result will be the second page, with two results, sorted by the first name.

