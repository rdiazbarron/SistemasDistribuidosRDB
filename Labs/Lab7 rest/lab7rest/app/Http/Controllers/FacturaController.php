<?php

namespace App\Http\Controllers;


use Illuminate\Http\Request;
use App\Models\Factura;

class FacturaController extends Controller
{
    public function index()
    {
       $facturas = Factura::all();
         return response()->json($facturas);
    }

    public function store(Request $request)
    {
        $factura = Factura::create($request->all());
        return response()->json($factura, 201);
    }
    
}
