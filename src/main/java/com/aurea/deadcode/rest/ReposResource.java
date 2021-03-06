package com.aurea.deadcode.rest;

import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;

import com.aurea.deadcode.rest.dto.BasicRepoData;
import com.aurea.deadcode.rest.dto.FullRepoData;
import com.aurea.deadcode.rest.dto.RepoListData;
import com.aurea.deadcode.rest.dto.NewRepoRequest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface ReposResource {
    @ApiOperation(value = "getAllRepos", produces = MediaType.APPLICATION_JSON,
            notes = "Gets the list of all repositories that have already been added along with their status.")
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = RepoListData.class),
            @ApiResponse(code = 500, message = "Failure")
    }) 
    ResponseEntity<RepoListData> getAllRepos();

    @ApiOperation(value = "addRepo",  produces = MediaType.APPLICATION_JSON,  consumes = MediaType.APPLICATION_JSON,
            notes = "Adds GitHub repository to be analyzed agains Dead Code.")
    @ApiParam(value = "Repository request i.e. URL and branch", required = true)
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = BasicRepoData.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 409, message = "Conflict - repository already exists", response = BasicRepoData.class),
            @ApiResponse(code = 500, message = "Failure")
    })
    ResponseEntity<BasicRepoData> addRepo(final NewRepoRequest request);

    @ApiOperation(value = "getRepo", produces = MediaType.APPLICATION_JSON,
            notes = "Gets the Dead Code occurrences from a given github repository.")
    @ApiParam(value = "Repository UUID", name = "uuid", required = true)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = FullRepoData.class),
            @ApiResponse(code = 404, message = "Repository Not Found"),
            @ApiResponse(code = 500, message = "Failure")
    })
    ResponseEntity<FullRepoData> getRepo(final String uuid);
}
