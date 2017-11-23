server:
  port: 8443
  ssl:
    key-store: file://localhost/D:/mykeys.jks
    key-store-password: letmein
    key-password: letmein

  
logging:
  path: D:/
  file: BookWorm.log
  level:
    root: WARN
    org:
      springframework:
        security: DEBUG
        
        
        
        --------------------
spring:
  thymeleaf: 
    cache: false
  profiles:
    active: development
    
amazon:
  associatedId: habuma-20
  
 
---
spring: 
  profiles: development

logging:
  level:
    root: DEBUG  
        
---

spring: 
  profiles: production
   


logging:
  path: D:/
  file: BookWorm.log
  level:
    root: WARN
  

---
server:
  port: 8484

spring:
  thymeleaf: 
    cache: false
  profiles:
    active: development
    
amazon:
  associatedId: habuma-20
  
 
---
spring: 
  profiles: development

#logging:
 # level:
    #root: DEBUG  
        
---

spring: 
  profiles: production
   


logging:
  path: D:/
  file: BookWorm.log
  level:
    root: WARN
  

  