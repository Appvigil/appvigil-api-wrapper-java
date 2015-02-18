Appvigil API Java wrapper
=========================

Generic: java -jar javawrapper-1.0.jar [options] \<parameters\>


Options	      |    Required	|    Parameter	|  Description
--------------|-------------|---------------|----------------
-K or --api-key |	Yes	| \<api-key\> |	 API key provided by Appvigil
-S or --api-secret |	Yes	| \<api-secret\> |	API secret provided by Appvigil
-L or --app-loc	| Yes |	\<app-loc\> |	Absolute location of your app with .apk extension
-A or --app-name |	Optional |	\<app-name\> |	Name of your app, Default: your apk file name
-C or --credential-id|	Optional|	\<credential-id\>	|Specify test credentials to login into the app, Default: null
-t or --ttl|	Optional|	\<ttl\>|	Set ttl (time to live) for access token, Default: 86400 sec
-h or --help|	No	|\<help\>|	Show help
-d or --disable_digest_check|	No|	\<disable_digest_check\>|	Disable digest before uploading your apk file




Example Usage:

java -jar javawrapper-1.0.jar –-api-key A085XX0934FD39D –-api-secret 591C09A1SS8CCF3E53B6B6A5E7AED3 –-app-loc myapp.apk


Online doc: https://appvigil.co/documentation/doku.php?id=java_wrappers
