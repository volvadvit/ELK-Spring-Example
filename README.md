#Spring applciation for test logging with Elasticsearch, Logstash, Kibana
![shema](https://github.com/volvadvit/ELK-Spring-Example/blob/master/1.png?raw=true)
__________
- Install & run [ELK stack](https://www.elastic.co/)
- Configure Logstash's **.conf** file. Add: 

```
  input {
    path => "path to .log file"
  }
  output {
    elasticsearch { hosts => ["localhost:9200"] }
    stdout { codec => rubydebug }
  }
```
  
- Uncommit `elasticsearch.hosts: ["http://localhost:9200"]` in **kibana.yml** file
- Refactor **application.yml** file in project's resource dir, add your path to .log file into `logging: file:`
_________
- Add logs by sending HTTP requests `http://localhost:9898/getUser/{id}`, **after running** spring application
- Go to `http://localhost:9200/_cat/indices/` and find `logstash-...` **index name**. You can check logs by sending `http://localhost:9200/_cat/indices/{index}`
- Go to `http://localhost:5601` and you get **Kibana** panel. Go to **"Stack Managment->Index Pattern"** and add new one, with name of your logstash index from the step above
- In Kibana go to Discover, choose you index. Now you can work with your logs.
