# Naught and Cross Game - Restful Webservice Implementation


Please find below the Rest Url for the Naught and Cross Game


# To create new game

Method : POST 

URL - {base-url}/rest/noughtcross/game/new

Input MIME Type : application/json
Output MIME Type: application/json

Input Content format example -

{"crossplayer:123,"noughtplayer:321"}


Output Reponse content example-

 {"id":1,"crossplayer:123,"noughtplayer:321"}


# To get status of the game 

Method : GET

URL - {base-url}/rest/noughtcross/game/{gameid}/status

Output mimetype : application/json

{"id":1,"board":{"cellList":[{"row":1,"column":1,"value":"-"},{"row":1,"column":2,"value":"-"},{"row":1,"column":3,"value":"-"},{"row":2,"column":1,"value":"-"},{"row":2,"column":2,"value":"-"},{"row":2,"column":3,"value":"-"},{"row":3,"column":1,"value":"-"},{"row":3,"column":2,"value":"-"},{"row":3,"column":3,"value":"-"}]},"gameStatus":"NEW","crossPlayer":{"id":123,"symbol":"X"},"noughtPlayer":{"id":321,"symbol":"O"},"lastPlayerId":0,"wonPlayerId":0}



#To play game

Method : PUT

URL : {base-url}/rest/noughtcross/play/{gameid}/{playerid}/{rowid}/{columnid}

