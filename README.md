# Solar_Designer_Application
//Masirika Tandema Narcisse
//Prototype

The software application purpose is to facilitate the sizing of solar-PV systems to operate a desalination plant. 
The software inputs are:

>Run the Solar_Designer_Application.exe 
>User selection of site on google map editior.
This selection will select the site latitude and longitude coordinates in degrees.
The default map is located at cape town which is -33.918861, 18.423300.

>Next, the coordinates are sent to NASA weather databases. This database is easily to use and generate a JSON file.
By passing the coordinates to the database, weather and solar data are retrieve by the software as JSON file.

>The user is required to input all the electrical secifications.
An example of data to input:
Load Power: 23.82
Time: 1.75

Battery
Voltage: 48
Amp-Hour: 1250
Depth: 80
Multiplier: 1.00
Efficiency: 95

PV
Power: 350
Voltage: 39.2
Coefficient o.oo4
Width: 0.99
Length: 1.95
Inverter: 90
Days: 3

>Different calculations are performed in the background. Results can be generated on the interface or save as a text file on the local storage.
