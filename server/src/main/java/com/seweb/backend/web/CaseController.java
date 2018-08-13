package com.seweb.backend.web;

import com.seweb.backend.framework.core.web.Request;
import com.seweb.backend.framework.core.web.Response;
import com.seweb.backend.framework.core.web.ResponseType;
import com.seweb.backend.repository.CaseRepository;
import com.seweb.backend.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class CaseController extends TextController {
    @Autowired
    private CaseService caseService;

    @RequestMapping(value = "/case")
    public Response queryAllCase(Request request) {
        Response response = new Response();
        try
        {
            response.status = ResponseType.SUCCESS;
            response.data = caseService.queryAllCase();
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

    @RequestMapping(value = "/queryCase")
    public Response queryCase(Request request){
        Response response = new Response();

        try
        {
            response.status = ResponseType.SUCCESS;
            response.data = caseService.queryCaseById(request.getParams());
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

    @RequestMapping(value = "/addCase")
    public Response addNews(Request request) {
        Response response = new Response();

        try {
            caseService.addCase(request.getParams());
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

    @RequestMapping(value = "/deleteCase")
    public Response deleteNews(Request request) {
        Response response = new Response();
        try {
            caseService.deleteCase(request.getParams());
            response.status = ResponseType.SUCCESS;
            response.data = caseService.queryCaseById(request.getParams());
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

    @RequestMapping(value = "/editCase")
    public Response editNews(Request request) {
        Response response = new Response();
        try {
            caseService.editCase(request.getParams());
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
