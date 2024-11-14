package com.example.proyectoidnp;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class CathedralMapView extends View {

    private Paint wallPaint, paintingPaint, textPaint, benchPaint;
    private RectF pintura1Rect, pintura2Rect, pintura3Rect, pintura4Rect, demonioRect;

    public CathedralMapView(Context context) {
        super(context);
        init();
    }

    public CathedralMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // Configuración de pinturas y texto
        wallPaint = new Paint();
        wallPaint.setColor(Color.BLACK);
        wallPaint.setStrokeWidth(10f);
        wallPaint.setStyle(Paint.Style.STROKE);

        paintingPaint = new Paint();
        paintingPaint.setColor(Color.rgb(245, 245, 220)); // Color crema

        benchPaint = new Paint();
        benchPaint.setColor(Color.LTGRAY); // Color gris claro para las bancas

        textPaint = new Paint();
        textPaint.setColor(Color.DKGRAY);
        textPaint.setTextSize(32f);
        textPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Dimensiones de la vista
        int width = getWidth();
        int height = getHeight();

        // Inicializar las áreas de las pinturas según el tamaño de la vista
        pintura1Rect = new RectF(120, 120, 220, 220);
        pintura2Rect = new RectF(width - 220, 120, width - 120, 220);
        pintura3Rect = new RectF(120, height - 220, 220, height - 120);
        pintura4Rect = new RectF(width - 220, height - 220, width - 120, height - 120);
        demonioRect = new RectF(100, height - 350, 220, height - 250);

        // Dibuja el plano y las áreas de las pinturas
        drawPaintingArea(canvas, pintura1Rect, "Pintura 1");
        drawPaintingArea(canvas, pintura2Rect, "Pintura 2");
        drawPaintingArea(canvas, pintura3Rect, "Pintura 3");
        drawPaintingArea(canvas, pintura4Rect, "Pintura 4");
        drawPaintingArea(canvas, demonioRect, "El Demonio de la Catedral");

        // Dibuja las paredes exteriores de la catedral
        canvas.drawRect(80, 80, width - 80, height - 80, wallPaint);

        // Puerta de entrada (en la parte inferior central)
        textPaint.setTextSize(28f);
        canvas.drawText("Entrada", width / 2 - 50, height - 40, textPaint);
        canvas.drawRect(width / 2 - 60, height - 80, width / 2 + 60, height - 50, paintingPaint);

        // Puerta de salida (en la parte superior central)
        canvas.drawText("Salida", width / 2 - 40, 50, textPaint);
        canvas.drawRect(width / 2 - 60, 80, width / 2 + 60, 110, paintingPaint);

        // Añadir texto para representar otras áreas de la catedral
        canvas.drawText("Nave Central", width / 2 - 100, height / 2 + 40, textPaint);
        canvas.drawText("Altar", width / 2 - 50, 140, textPaint);
        canvas.drawText("Capillas Laterales", 100, height / 2 + 140, textPaint);
        canvas.drawText("Capillas Laterales", width - 340, height / 2 + 140, textPaint);

        // Banca para oración (dibujo de rectángulos en serie)
        drawBenches(canvas, width / 2 - 150, height / 2 + 60, width / 2 + 150, height / 2 + 90, 4, 30);
    }

    private void drawPaintingArea(Canvas canvas, RectF rect, String label) {
        // Dibuja cuadro de pintura en color crema y borde negro
        canvas.drawRect(rect, paintingPaint);
        canvas.drawRect(rect, wallPaint);
        canvas.drawText(label, rect.left + 10, rect.top + 60, textPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();

            // Comprobar en qué área se hizo el toque y mostrar la imagen correspondiente
            if (pintura1Rect.contains(x, y)) {
                showPaintingDialog(R.drawable.pintura_1);
            } else if (pintura2Rect.contains(x, y)) {
                showPaintingDialog(R.drawable.pintura_2);
            } else if (pintura3Rect.contains(x, y)) {
                showPaintingDialog(R.drawable.pintura_3);
            } else if (pintura4Rect.contains(x, y)) {
                showPaintingDialog(R.drawable.pintura_4);
            } else if (demonioRect.contains(x, y)) {
                showPaintingDialog(R.drawable.demonio_catedral);
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    private void showPaintingDialog(int drawableId) {
        // Mostrar un cuadro de diálogo con la imagen de la pintura seleccionada
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(drawableId);
        builder.setView(imageView);
        builder.setPositiveButton("Cerrar", null);
        builder.show();
    }

    // Método para dibujar bancas en fila
    private void drawBenches(Canvas canvas, float left, float top, float right, float bottom, int count, float spacing) {
        for (int i = 0; i < count; i++) {
            canvas.drawRect(left, top + i * spacing, right, bottom + i * spacing, benchPaint);
        }
    }
}





