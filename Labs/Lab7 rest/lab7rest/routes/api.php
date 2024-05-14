<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\FacturaController;
use App\Http\Controllers\LoginController;
use App\Http\Middleware\JWTMiddleware;

// use App\Http\Controllers\ProfileController;

Route::get('/user', function (Request $request) {
    return $request->user();
})->middleware('auth:sanctum');

Route::apiResource('v1/facturas', FacturaController::class)->middleware(JWTMiddleware::class);
Route::post('login',[LoginController::class,'login']);//login no tiene mideleware