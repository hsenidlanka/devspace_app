package hsl.devspace.app.admin.packagemanagement.spring.controller;

import hsl.devspace.app.corelogic.domain.Package;
import hsl.devspace.app.corelogic.repository.Package.PackageRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLIntegrityConstraintViolationException;

@Controller
@RequestMapping(value = "/packages")
public class PackageController {

    private static final Logger LOG = LogManager.getLogger(PackageController.class);

    @Autowired
    private PackageRepository itemPackage;

    /**
     * Add new package view
     **/

    //For viewing the add package form
    @RequestMapping(value = "/addPkg",method = RequestMethod.GET)
    public ModelAndView showAddPackage(){
        return new ModelAndView("pkgadd", "command",new Package());
    }

    //For submitting the add new package
    @RequestMapping(value = "/add_package",method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("addPackage") hsl.devspace.app.corelogic.domain.Package newPackg) throws SQLIntegrityConstraintViolationException {
        ModelAndView model = new ModelAndView();

        int p = itemPackage.add(newPackg);
        if(p == 1)
            model.setViewName("addPackage");
        else
            System.out.println("Error in package add");

        return model;
    }
}
