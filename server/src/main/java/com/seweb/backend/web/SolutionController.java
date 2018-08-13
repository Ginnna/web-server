package com.seweb.backend.web;

import com.seweb.backend.domain.Solution;
import com.seweb.backend.framework.core.web.Request;
import com.seweb.backend.framework.core.web.Response;
import com.seweb.backend.framework.core.web.ResponseType;
import com.seweb.backend.service.NewsService;
import com.seweb.backend.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class SolutionController extends BaseController{
    @Autowired
    private SolutionService solutionService;

    @RequestMapping(value = "/Solution")
    public Response queryAllSolution(Request request)
    {
        Response response = new Response();
        try
        {
            response.status = ResponseType.SUCCESS;
            response.data = solutionService.queryAllSolution();
            response.message = "";
        }
        catch(Exception e)
        {
            e.printStackTrace();
            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }
        return response;
    }

    @RequestMapping(value = "/querySolution")
    public Response querySolution(Request request){
        Response response = new Response();

        try
        {
            response.status = ResponseType.SUCCESS;
            response.data = solutionService.querySolutionById(request.getParams());
            response.message = "";

        }
        catch(Exception e)
        {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/addSolution")
    public Response addSolution(Request request) {
        Response response = new Response();

        try {
            solutionService.addSolution(request.getParams());
            response.status = ResponseType.SUCCESS;

        }
        catch(Exception e)
        {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/deleteSolution")
    public Response deleteNews(Request request) {
        Response response = new Response();
        try {
            solutionService.deleteSolution(request.getParams());
            response.status = ResponseType.SUCCESS;
            response.message = "";

        }
        catch(Exception e)
        {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/editSolution")
    public Response editNews(Request request) {
        Response response = new Response();
        try {
            solutionService.editSolution(request.getParams());
            response.status = ResponseType.SUCCESS;

        }
        catch(Exception e)
        {
            e.printStackTrace();
            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }
}
