# Project highlights how, usind CDK, to expose an EC2-based public REST API via a non-standard HTTP port
# Also illustrates how to load custom code via a user-data script

# Project creates AWS artifacts:
#  - VPC
#  - EC2
#  - S3
#  - A sample Java-based REST API

# User data loads from S3 a Linux JDK and a demo REST API
# This is a load balanced version of https://github.com/paul-hyndman/cdk-ec2-webserver-on-vpc which deploys a Java REST API
# The API contains code for the Java Interview Question published at https://github.com/paul-hyndman/java-interview-question
# When deployed, do a GET using example URL, passing a comma-delimited list of names.  API example below returns most common name
# http://webserveralb-934497190.us-east-1.elb.amazonaws.com:1025/mostpopular/jane insured,jane insured,joe insured,joe insured,homer simpson,homer simpson,homer simpson, marge simpson


Requirements:
 - A command shell such as Git Bash
 - Python
 - CDK
 - Node JS/NPM for miscellaneous package installs
 - A linux JDK

Modify cdk.json with your account in the "envs" configuration

To create the web server:<br>
    1. Run pip install -r requirements.txt from root of project<br>
    2. Modify app.py to deploy S3 buket (uncomment line "S3CreateProject(app, "S3CreateProject", env=env_dev)")<br>
	   - Build REST API project and upload .jar file to S3 bucket
&nbsp;&nbsp;&nbsp;- From project root run "cdk init" to check for errors, then "cdk deploy"<br>
    3. From root of project copy html file to S3<br>
&nbsp;&nbsp;&nbsp;-  aws s3 cp index.html s3://<<your bucket name>>/index.html<br>
    4. Modify app.py to deploy VPC (uncomment line "CustomVpcStack(app, "CustomVpcStack", env=env_dev)")<br>
&nbsp;&nbsp;&nbsp;- From project root run "cdk init" to check for errors, then "cdk deploy CustomVpcStack --require-approval never"<br>
&nbsp;&nbsp;&nbsp;- This can take a while to deploy<br>
    5. Verifying deploy of VPC on AWS console<br>
&nbsp;&nbsp;&nbsp;- From AWS console you can find it via VPC->You VPCs<br>
    6. Modify app.py to deploy EC2 (uncomment line "CustomVpcStack(app, "CustomVpcStack", env=env_dev)"<br>
&nbsp;&nbsp;&nbsp;- Modify class CustomEc2Stack to use ID of VPC created in step #4<br>
&nbsp;&nbsp;&nbsp;- From project root run "cdk init" to check for errors, then "cdk deploy CustomEc2Stack --require-approval never"<br>
    7.  Use example URL below (IP address or public LB) to send GET request to app:
	    # http://webserveralb-934497190.us-east-1.elb.amazonaws.com:1025/mostpopular/jane insured,jane insured,joe insured,joe insured,homer simpson,homer simpson,homer simpson, marge simpson
&nbsp;&nbsp;&nbsp;- It may take a couple minutes for AWS to recognize/load the app.  System may show 503 Bad Gateway until readys<br>
