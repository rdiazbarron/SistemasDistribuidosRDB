<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Cliente;

class ClienteController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return Cliente::all();
        
    }

    public function buscar(Request $request)
    {
       
        // Recuperar los parámetros de la solicitud
    $ci = $request->input('ci');
    $nombres = $request->input('nombres');
    $apellidos = $request->input('apellidos');

    // Realizar la consulta utilizando Eloquent
    $clientes = Cliente::where('ci', $ci)
                       ->where('nombres', $nombres)
                       ->where('apellidos', $apellidos)
                       ->get();

    // Devolver los datos en formato JSON
    return response()->json($clientes);
    }
}
