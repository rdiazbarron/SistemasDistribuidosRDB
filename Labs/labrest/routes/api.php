<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\FacturaController;
use App\Http\Controllers\LoginController;
use App\Http\Controllers\ClienteController;
use App\Http\Controllers\TituloController;
use App\Http\Controllers\TempsController;

Route::get('/cliente', 'ClienteController@buscar');

Route::get('/user', function (Request $request) {
    return $request->user();
})->middleware('auth:sanctum');


Route::post('login', [LoginController::class, 'login']);

Route::apiResource('v1/facturas', FacturaController::class);  

Route::apiResource('v1/titulos', TituloController::class);  


Route::apiResource('v1/temps', TempsController::class);
//Route::apiResource('v1/facturas', FacturaController::class);

