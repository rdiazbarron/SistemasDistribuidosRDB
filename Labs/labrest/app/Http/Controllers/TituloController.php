<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Titulo; 

class TituloController extends Controller
{
    public function store(Request $request)
    {
    
        $data = $request->all();
        $titulo = Titulo::create
        ([
            'ci' => $data['ci'],
            'nombre' => $data['nombre'],
            'apellido_paterno' => $data['apellido_paterno'],
            'apellido_materno' => $data['apellido_materno'],
            'titulo' => $data['titulo']
        ]);

        return response()->json($titulo, 201);
    }

    public function show(string $id)
    {
        return Titulo::find($id);
    }

    public function index()
    {
        return Titulo::all();
    }


}