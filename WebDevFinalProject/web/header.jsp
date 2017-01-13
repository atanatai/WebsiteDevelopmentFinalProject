<%-- 
    Document   : header
    Created on : May 1, 2016, 12:37:24 PM
    Author     : Atan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>&Kopf;Rahm Proj</title>
        <meta charset="UTF-8">
        <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="main.css" type="text/css" /> 
    </head>
        <header>
            <div class="topLeft">
                ${userMessage}
            </div>
            <h2 id='heading'>${message}</h2>
        </header>