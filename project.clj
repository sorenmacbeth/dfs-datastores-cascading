(defproject backtype/dfs-datastores-cascading "1.0.4"
  :java-source-path "src/jvm"
  :javac-options {:debug "true" :fork "true"}
  :repositories {
                 "conjars" "http://conjars.org/repo"
                 }  

  :dependencies [
                 [backtype/dfs-datastores "1.0.3"]
                 [cascading/cascading-core "1.2-wip-63" :exclusions [org.codehaus.janino/janino]]
                 ]
  :dev-dependencies [
                     [org.apache.hadoop/hadoop-core "0.20.2-dev"]
                     [junit/junit "3.8.2"]
                    ])
