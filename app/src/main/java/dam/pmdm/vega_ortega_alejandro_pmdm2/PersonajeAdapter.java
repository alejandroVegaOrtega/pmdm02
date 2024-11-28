package dam.pmdm.vega_ortega_alejandro_pmdm2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adaptador para mostrar una lista de personajes
 * Crea las vistas para cada elemento de la lista,
 * asignar los datos a las vistas y manejar los clicks en los elementos.
 */
public class PersonajeAdapter extends RecyclerView.Adapter<PersonajeAdapter.PersonajeViewHolder> {

    private List<Personaje> listaPersonajes;
    private Context context;
    private OnItemClickListener listener;
    /**
     * Interfaz para manejar los clics en los elementos de la lista.
     */
    public interface OnItemClickListener {
        /**
         * Método que se llama cuando se hace clic en un elemento de la lista.
         *
         * @param personaje El personaje que ha sido seleccionado.
         */
        void onItemClick(Personaje personaje);
    }

    /**
     * Constructor del adaptador.
     *
     * @param context El contexto de la aplicación.
     * @param listaPersonajes La lista de personajes a mostrar.
     * @param listener El listener que manejará los clics en los elementos.
     */
    public PersonajeAdapter(Context context, List<Personaje> listaPersonajes, OnItemClickListener listener) {
        this.context = context;
        this.listaPersonajes = listaPersonajes;
        this.listener = listener;
    }

    /**
     * Crea una nueva vista para un elemento de la lista.
     * @param parent El ViewGroup que contendrá la vista inflada.
     * @param viewType El tipo de vista que se va a crear
     * @return Un nuevo PersonajeViewHolder que contiene la vista creada.
     */
    @NonNull
    @Override
    public PersonajeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_personaje, parent, false);
        return new PersonajeViewHolder(view);
    }

    /**
     * Asocia los datos de un personaje con una vista en el ViewHolder.
     * @param holder El PersonajeViewHolder que contiene las vistas para cada item.
     * @param position La posición del personaje en la lista.
     */
    @Override
    public void onBindViewHolder(@NonNull PersonajeViewHolder holder, int position) {
        Personaje personaje = listaPersonajes.get(position); //Obtiene objeto
        holder.textViewNombre.setText(personaje.getNombre()); //Asigna nombre al textView
        holder.imageView.setImageResource(personaje.getImagenId()); //Asigna imagen al textView
        holder.itemView.setOnClickListener(v -> listener.onItemClick(personaje)); //Maneja el click, en este caso llama al metodo onItemClick
    }

    /**
     * Devuelve el número total de elementos en la lista de personajes.
     *
     * @return El número total de elementos en la lista.
     */
    @Override
    public int getItemCount() {
        return listaPersonajes.size();
    }

    /**
     * ViewHolder para representar un personaje en la lista.
     */
    public static class PersonajeViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewNombre;
        TextView textViewDescripcion;

        /**
         * Constructor para inicializar las vistas del item.
         * @param itemView La vista del item que contiene los elementos visuales del personaje.
         */
        public PersonajeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewDescripcion = itemView.findViewById(R.id.textViewDescripcion);
        }
    }
}