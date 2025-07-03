package com.example.Dashboard2.Service;

import com.example.Dashboard2.Entity.PurchaseOrder;
import com.example.Dashboard2.Repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    // Create or update a purchase order
    public PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }

    // Get all purchase orders
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }
    public PurchaseOrder editPurchaseOrder(Long id, PurchaseOrder updatedOrder) {
        return purchaseOrderRepository.findById(id).map(existingOrder -> {
            existingOrder.setVendorName(updatedOrder.getVendorName());
            existingOrder.setClientName(updatedOrder.getClientName());
            existingOrder.setDate(updatedOrder.getDate());
            existingOrder.setSiteIncharge(updatedOrder.getSiteIncharge());
            existingOrder.setSiteInchargeMobileNumber(updatedOrder.getSiteInchargeMobileNumber());
            existingOrder.setENo(updatedOrder.getENo());
            existingOrder.setPurchaseTable(updatedOrder.getPurchaseTable());
            return purchaseOrderRepository.save(existingOrder);
        }).orElseThrow(() -> new RuntimeException("Purchase Order with ID " + id + " not found."));
    }
    // Delete purchase order by ID
    public String deletePurchaseOrder(Long id) {
        if (purchaseOrderRepository.existsById(id)) {
            purchaseOrderRepository.deleteById(id);
            return "Purchase Order with ID " + id + " deleted.";
        } else {
            throw new RuntimeException("Purchase Order with ID " + id + " not found.");
        }
    }

    // Delete all purchase orders
    public String deleteAllPurchaseOrders() {
        purchaseOrderRepository.deleteAll();
        return "All purchase orders have been deleted.";
    }
}
