# Kata 

Solution for [kata word chains](http://codekata.com/kata/kata19-word-chains).

[![Build Status](https://travis-ci.com/vonsowic/kata-word-chains.svg?branch=master)](https://travis-ci.com/vonsowic/kata-word-chains?branch=master)
[![codecov](https://codecov.io/gh/vonsowic/kata-word-chains/branch/master/graph/badge.svg)](https://codecov.io/gh/vonsowic/kata-word-chains)
[![codebeat badge](https://codebeat.co/badges/b3cb5357-1f96-4b24-9c66-4a3672c344fc)](https://codebeat.co/projects/github-com-vonsowic-kata-word-chains-master)

## Manual test
Service is available on AWS. Check example requests:
 - [cat-dog](http://ec2-18-197-109-160.eu-central-1.compute.amazonaws.com:8080/api/chain?start=cat&end=dog)
 - [ruby-code](http://ec2-18-197-109-160.eu-central-1.compute.amazonaws.com:8080/api/chain?start=ruby&end=code)
 - [gold-lead](http://ec2-18-197-109-160.eu-central-1.compute.amazonaws.com:8080/api/chain?start=gold&end=lead)


## How to execute
`gradle run`
