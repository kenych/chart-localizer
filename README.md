**Chart localiser**


**Requirements**
 
This application is given a chart with popular songs and it should return localised version which is defined by second parameter

It should run from the command line in the following way:

```user$ java -jar popular-tracks.jar chart.json it```
(it for italy)

The resulting chart should be written to 'localised-chart.json', which should
be located in the same directory as the unlocalised chart. The chart should
have the following format:

```
    {
    	"title": "Server Team Top Tracks",
    	"entries": [
    		{
    			"trackTitle": "Get Lucky",
    			"artistName": "Daft Punk Feat. Pharrell Williams"
    		},
    		{
    			"trackTitle": "Il Ballo Del Qua Qua",
    			"artistName": "Romina Power"
    		}
    		...
    	]
    }
```

If a localisation is not available for the language passed as a command line
argument, then the 'en' localisation should be used.

**Solution**


PopularTracksAcceptanceTest is testing pretty much all cases:
1) successful case, by checking predefined resulting json
2) fail case: when corrupted json is supplied
3) fail case: when file not found
4) fail case: when arguments are incorrect

The rest of the tests are mostly unit testing per major class with some business logic.
Value/Data classes are not unit tested, as this would be slightly redundant.
Output file name and place is also more or less configurable as it is generated with
specific implementation (InputFileDirectoryBasedPolicy) of OutputFilePathPolicy interface.

It should be probably good to have more exceptions thrown per case rather than simply using Runtime
or IllegalArgument but as application is very little I decided to skip it(only one created for Language support)

There is no performance related issue addressed.
