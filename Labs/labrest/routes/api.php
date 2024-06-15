<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\FacturaController;
use App\Http\Controllers\LoginController;
use App\Http\Controllers\ClienteController;
use App\Http\Controllers\TituloController;

Route::get('/cliente', 'ClienteController@buscar');

Route::get('/user', function (Request $request) {
    return $request->user();
})->middleware('auth:sanctum');


Route::post('login', [LoginController::class, 'login']);
// Route::middleware('jwt.auth')->group(function () {
//     Route::apiResource('v1/facturas', FacturaController::class);
// });
Route::apiResource('v1/facturas', FacturaController::class);  

#Route::post('/titulos', [TituloController::class, 'store']);

Route::apiResource('v1/titulos', TituloController::class);  
//ruta para solamente solo un id:
// Route::apiResource('v1/facturas/{id}/', FacturaController::class);  
//Route::middleware('jwt.auth')->apiResource('v1/facturas', FacturaController::class);


//Route::apiResource('v1/facturas', FacturaController::class);

