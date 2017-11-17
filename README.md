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