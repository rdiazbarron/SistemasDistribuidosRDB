<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        
    // protected $fillable = ['banco','nro_cuenta','ci', 'nombres', 'apellidos', 'saldo'];
        Schema::create('clientes', function (Blueprint $table) {
            $table->id();
            $table->string('banco', 50);
            $table->string('nro_cuenta', 50);
            $table->integer('ci');
            $table->string('nombres', 50);
            $table->string('apellidos', 50);
            $table->float('saldo',6,2);
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('clientes');
    }
};
