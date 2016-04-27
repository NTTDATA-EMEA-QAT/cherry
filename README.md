# Cherry

[![Join the chat at https://gitter.im/MagenTys/cherry](https://badges.gitter.im/MagenTys/cherry.svg)](https://gitter.im/MagenTys/cherry?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.magentys/cherry/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.magentys/cherry)

A Syntactical Sugar project implementing the Mission pattern.

**Full Documentation can be found at: [http://magentys.github.io/cherry](http://magentys.github.io/cherry)**

## How to install

### Maven
Just add the following config in your dependencies:

            <dependency>
              <groupId>io.magentys</groupId>
              <artifactId>cherry</artifactId>
              <version>0.0.5</version>
            </dependency>

### Gradle
Add the following in your build.gradle

            runtime group: 'io.magentys', name: 'cherry', version: '0.0.5'
            
## Ivy

Add the following in your ivy.xml

            <dependency org="io.magentys" name="cherry" rev="0.0.5"/>

## SBT

In your build.sbt your can add the following:

          libraryDependencies += "io.magentys" % "cherry" % "0.0.5"
          
## How to use:

The 3 main concepts are:
        
1. **Agent** - your test agent who can:
    * obtain and use tools
    * store and retrieve objects of any type in his memory
    *  perform missions which can return results 
2. **Memory** - an agent-specific cache to be used to store & retrieve objects of any type
3. **Mission** - it's an interface which can return any type of results
        
Steps:

1. Import statically in your class:

        import static io.magentys.AgentProvider.agent;

2. Create an Agent (e.g. in your @Before of your tests)
    
        Agent agent = agent();
        
3. Assign tools (objects of any type e.g. WebDriver, dbClient etc.):
        
        agent.obtains(webDriver, dbClient);
        
4. Store values (objects of any type) to his memory using a unique id (String):      
  
        agent.keepInMind("unique.key", new MyWonderfulType());
        
    and retrieve them :
        
        MyWonderfulType myType = agent.recalls("unique.key", MyWonderfulType.class);
        
5. Create a Mission with a result:

            Mission<String> retrievalOfUsername = new Mission<String>() {
            
                @Override
                public String accomplishAs(Agent agent) {
                    WebDriver webDriver = agent.using(WebDriver.class);
                    String visibleText = webDriver.findElement(By.cssSelector("#username").getText();
                    return visibleText;
                }
            }
        
    and perform as follows:
    
           String username = agent.performs(retrievalOfUsername);
           
6. You can perform Missions without a result:
     
            Mission<Agent> importantMission = new Mission<Agent>() {
            
                @Override
                public Agent accomplishAs(Agent agent) {
                    /*
                       you code goes here
                    */
                    return agent;
                }
            }         
     
     
     In that way you can chain actions together:
          
          Agent salesPerson = agent().obtains(sshClient, restfulClient, webDriver, dbClient);
          
          agent.performs(sshAuthentication, restfulAuthentication, webAuthentication, dbAuthentication); // all of them are missions
          
          // or
          
          Agent Tom = agent();
                  Tom.obtains(aPrinter(), and(aScanner()))
                          .andHe( scansThe("important Document"), and(printsTheDocument()));
                          
          
          
          
- Developers: [Kostas Mamalis](https://twitter.com/mamalisk)

Powered by: [MagenTys](http://magentys.io)            
            

