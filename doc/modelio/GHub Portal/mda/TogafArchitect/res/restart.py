from com.modeliosoft.modelio.api.mdac import IMdac
from com.modeliosoft.modelio.api.mdac import MdacException
from com.modeliosoft.modelio.core.mdacs import IMdacRegistry
from com.modeliosoft.modelio.mdainfra.mdacs.core import MdacManager
from com.modeliosoft.modelio.core.app import O


session = O.getDefault ().getModelingSession ()
registry = session.getMdacRegistry ()
manager = MdacManager (session)

t = modelingSession.createTransaction("reload TogafArchitect")

for mdacmodel in session.getModel().getProject().getInstalled():
	print mdacmodel
	if (mdacmodel.getName () == "TogafArchitect"):
		currentMdac = registry.getLoadedMdac (mdacmodel)
		if (currentMdac != None):
			print "stopping", currentMdac
			manager.stopMdac(currentMdac)
			print "unloading", currentMdac
			manager.unloadMdac (currentMdac)
		print "loading", mdacmodel
		currentMdac = manager.loadMdac(mdacmodel)

		if (currentMdac != None):
			print "starting", currentMdac
			manager.startMdac(currentMdac)

modelingSession.commit (t)
