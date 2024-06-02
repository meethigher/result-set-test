对于PostgreSQL的JDBC驱动来说，

默认情况下，驱动程序会一次性收集查询的所有结果。这对于大型数据集来说可能不方便，因此 JDBC 驱动程序提供了一种`ResultSet`基于数据库游标并仅提取少量行的方法。

连接的客户端会缓存少量行，当缓存用尽时，将通过重新定位游标来检索下一块行。

[PostgreSQL: Documentation: 7.4: Issuing a Query and Processing the Result](https://www.postgresql.org/docs/7.4/jdbc-query.html)

[Issuing a Query and Processing the Result | pgJDBC](https://jdbc.postgresql.org/documentation/query/#getting-results-based-on-a-cursor)