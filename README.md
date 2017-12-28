# PersonalMybatisGeneratorForMySQL
用于生成，基于mybatis，springmvc，restful接口的Controller、Service、Dao、Mapper文件


暂定：
1.主键必须为第一列，且字段名为id，类型为int或者bigint;
2.每张表必须有一个字段:time_delete，类型为bigint，default为0，用于标记记录是否删除，0为未删除，删除为记录Java时间戳。



