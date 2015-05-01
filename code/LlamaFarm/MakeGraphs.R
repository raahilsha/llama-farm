toanalyze <- dir(getwd(), pattern = "*.csv", full.names = TRUE, ignore.case = TRUE)
for (csvfile in toanalyze)
{
	dataset <- read.csv(csvfile,header=T)

	newfilen <- paste(substring(csvfile, 0, 81), "_ticks.png", sep = "")
	png(filename=newfilen,width=512,height=512)
	plot(dataset[,1],dataset[,2],xlab="Generation Number",ylab="Ticks Survived",main="Ticks Survived over Time")
	dev.off()

	newfilen <- paste(substring(csvfile, 0, 81), "_genes.png", sep = "")
	png(filename=newfilen,width=512,height=512)
	
	plot(dataset[,1],dataset[,3],col=1,pch=1,ylim=c(0,1),main="Genes Representation over Time",xlab="Generation Number",ylab="Index")
	legend(15,1, c("Hunger","Violence","Laziness","Metabolism","Style"),lwd=c(2.5,2.5),col=1:5)
	par(new=TRUE)
	plot(dataset[,1],dataset[,4],col=2,pch=1,ylim=c(0,1),axes = F, xlab = NA, ylab = NA)
	par(new=TRUE)	
	plot(dataset[,1],dataset[,5],col=3,pch=1,ylim=c(0,1),axes = F, xlab = NA, ylab = NA)
	par(new=TRUE)
	plot(dataset[,1],dataset[,6],col=4,pch=1,ylim=c(0,1),axes = F, xlab = NA, ylab = NA)
	par(new=TRUE)
	plot(dataset[,1],dataset[,7],col=5,pch=1,ylim=c(0,1),axes = F, xlab = NA, ylab = NA)

	dev.off()
}
