package iconqweb.test

class TestUtils {

    def static apagarBanco(sql){
        executarScriptSql new File("test/resources/sql/apagaTudo.sql"), sql
    }

    def static executarScriptSql(file, sql){
        def sqlFile = file.text
        executarScriptSqlString sqlFile, sql
    }

    def static executarScriptSqlString(string, sql){
        try{
            def sqlCommands = new StringBuffer()
            string.readLines().each{ line ->  sqlCommands.append line }
            sqlCommands.toString().split(";").each{ command ->
                sql.execute command
            }
        }
        catch(Exception e){
            throw new RuntimeException(e)
        } finally {
            sql.commit()
        }
    }
}
